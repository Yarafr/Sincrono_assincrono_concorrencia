// Pacote do projeto do Cliente (o mesmo da TelaTotem)
package com.mycompany.clienteatendimentorpc;

// Classe gerada pelo protoc: contém os stubs (bloqueante e assíncrono) do serviço
import com.mycompany.atendimentorpc.AtendimentoServiceGrpc;
// Mensagem de resposta do ListarSenhas (Parte 1)
import com.mycompany.atendimentorpc.ListaSenhas;
// Mensagem que o totem envia ao pedir uma senha
import com.mycompany.atendimentorpc.PedidoSenha;
// Mensagem que a Central devolve com a senha
import com.mycompany.atendimentorpc.Senha;
// Enum NORMAL / PRIORITARIO
import com.mycompany.atendimentorpc.TipoAtendimento;
// Mensagem vazia usada como entrada do ListarSenhas (Parte 1)
import com.mycompany.atendimentorpc.Vazio;
// Canal = a conexão com a Central
import io.grpc.ManagedChannel;
// Ajuda a criar o canal
import io.grpc.ManagedChannelBuilder;
// Interface do callback: onNext, onError, onCompleted
import io.grpc.stub.StreamObserver;
// Conjunto que não aceita elementos repetidos
import java.util.HashSet;
// Interface de conjunto
import java.util.Set;
// Contador regressivo: deixa o main esperar as 200 respostas chegarem
import java.util.concurrent.CountDownLatch;
// Unidade de tempo (segundos) usada na espera
import java.util.concurrent.TimeUnit;

// Totem de teste: dispara 200 pedidos ao mesmo tempo usando o stub ASSÍNCRONO
public class TotemAssincrono {

    // Quantidade de pedidos disparados de uma vez (a frase da atividade)
    private static final int TOTAL = 200;

    // Números de senha recebidos (o Set descarta os repetidos automaticamente)
    private static final Set<Integer> numeros = new HashSet<>();
    // Quantas respostas chegaram com número que já tinha aparecido
    private static int repetidas = 0;
    // Quantas respostas chegaram no total (onNext)
    private static int respostas = 0;
    // Quantas chamadas falharam (onError)
    private static int erros = 0;

    // synchronized: os callbacks chegam em várias threads ao mesmo tempo,
    // e o HashSet e os contadores não são seguros para várias threads
    private static synchronized void registrar(int numero) {
        // conta mais uma resposta recebida
        respostas++;
        // add devolve false quando o número já estava no conjunto
        if (!numeros.add(numero)) {
            // número repetido: soma no contador de repetidas
            repetidas++;
        }
    }

    // synchronized pelo mesmo motivo: vários callbacks podem falhar juntos
    private static synchronized void registrarErro() {
        // conta mais uma chamada com erro
        erros++;
    }

    // throws Exception porque await() pode lançar InterruptedException
    public static void main(String[] args) throws Exception {
        // Abre a conexão com a Central (a mesma porta do Servidor)
        ManagedChannel canal = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // sem TLS: só no laboratório
                .build();

        // Stub ASSÍNCRONO: a chamada volta na hora e a resposta chega por callback
        AtendimentoServiceGrpc.AtendimentoServiceStub assincrono =
                AtendimentoServiceGrpc.newStub(canal);

        // Começa em 200; cada resposta (ou erro) diminui 1; ao chegar a 0, o await libera
        CountDownLatch fim = new CountDownLatch(TOTAL);

        // Guarda o instante de início para medir o tempo total
        long inicio = System.currentTimeMillis();

        // Repete 200 vezes, sem esperar nenhuma resposta entre uma e outra
        for (int i = 1; i <= TOTAL; i++) {
            // Monta o pedido (cada um com um nome diferente)
            PedidoSenha pedido = PedidoSenha.newBuilder()
                    .setNome("Cliente " + i)            // nome da pessoa
                    .setTipo(TipoAtendimento.NORMAL)    // atendimento normal
                    .setLocal("Teste 200 pedidos")      // identifica este totem
                    .build();                           // finaliza a mensagem

            // Dispara o pedido: NÃO bloqueia, o loop segue para o próximo na hora
            // O segundo parâmetro é o callback que vai receber a resposta depois
            assincrono.pegarSenha(pedido, new StreamObserver<Senha>() {
                // Chamado quando a Central envia a Senha
                @Override
                public void onNext(Senha senha) {
                    // anota o número recebido para detectar repetição
                    registrar(senha.getNumero());
                }

                // Chamado se a chamada falhar (Central fora do ar, prazo, etc.)
                @Override
                public void onError(Throwable erro) {
                    // conta o erro
                    registrarErro();
                    // esta chamada terminou (com erro): diminui o contador
                    fim.countDown();
                }

                // Chamado quando a Central encerra a chamada com sucesso
                @Override
                public void onCompleted() {
                    // esta chamada terminou: diminui o contador
                    fim.countDown();
                }
            });
        }

        // Espera até 30 s pelas 200 respostas; devolve false se estourar o prazo
        boolean terminou = fim.await(30, TimeUnit.SECONDS);
        // Calcula quanto tempo passou desde o início
        long tempo = System.currentTimeMillis() - inicio;

        // Mostra o resultado no console
        System.out.println("Terminou no prazo? " + terminou);
        System.out.println("Pedidos disparados: " + TOTAL);
        System.out.println("Respostas recebidas: " + respostas);
        System.out.println("Números distintos: " + numeros.size());
        System.out.println("Números repetidos: " + repetidas);
        System.out.println("Erros: " + erros);
        System.out.println("Tempo total: " + tempo + " ms");

        // Confere na Central quantas senhas ela guardou (usa o ListarSenhas da Parte 2)
        // Aqui o stub é BLOQUEANTE de propósito: queremos esperar a resposta
        ListaSenhas lista = AtendimentoServiceGrpc.newBlockingStub(canal)
                .listarSenhas(Vazio.getDefaultInstance());
        // getSenhasCount() é gerado pelo protoc a partir do campo repeated "senhas"
        System.out.println("Senhas guardadas na Central: " + lista.getSenhasCount());

        // Fecha a conexão para o programa poder terminar
        canal.shutdown();
    }
}
