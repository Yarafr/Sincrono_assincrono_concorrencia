// Pacote (pasta lógica) onde esta classe fica no projeto do Servidor
package com.mycompany.servidoratendimentorpc;

// Classe base gerada pelo protoc a partir do "service AtendimentoService" do .proto
import com.mycompany.atendimentorpc.AtendimentoServiceGrpc;
// Mensagem de resposta do ListarSenhas
import com.mycompany.atendimentorpc.ListaSenhas;
// Mensagem que o totem envia ao pedir uma senha
import com.mycompany.atendimentorpc.PedidoSenha;
// Mensagem que a Central devolve com a senha
import com.mycompany.atendimentorpc.Senha;
// Enum NORMAL / PRIORITARIO
import com.mycompany.atendimentorpc.TipoAtendimento;
// Mensagem vazia usada como entrada do ListarSenhas
import com.mycompany.atendimentorpc.Vazio;
// Objeto por onde enviamos a resposta de volta ao totem
import io.grpc.stub.StreamObserver;
// Lista comum do Java (sem biblioteca extra)
import java.util.ArrayList;
// Interface de lista
import java.util.List;

// A Central implementa o serviço; ImplBase traz os métodos que vamos sobrescrever
public class AtendimentoServiceImpl
        extends AtendimentoServiceGrpc.AtendimentoServiceImplBase {

    // ===== CHAVES DOS TESTES: mude o valor, salve e REINICIE a Central =====

    // NOVO (Parte 4) - Teste 2: true = contador protegido; false = sem proteção
    private static final boolean USAR_SYNCHRONIZED = false;

    // NOVO (Parte 4) - Teste 1: atraso de cada pedido em milissegundos
    // 0 = sem atraso (testes de 200 pedidos); 3000 = 3 segundos (teste síncrono x assíncrono)
    private static final long ATRASO_MS = 0;

    // NOVO (Parte 4) - pausa de 1 ms entre LER e GRAVAR o contador
    // Não cria o erro: só aumenta a chance de ele aparecer. Com 0, o erro continua
    // possível, mas pode não aparecer em todas as rodadas
    private static final long PAUSA_CORRIDA_MS = 1;

    // ===== FIM DAS CHAVES =====

    // Estado compartilhado: todos os totens usam o mesmo contador
    private int proximoNumero = 1;

    // ALTERADO (Parte 4): o synchronized saiu da assinatura e virou um bloco com chave
    private int gerarNumero() {
        // se a chave estiver ligada, só uma thread por vez entra no bloco
        if (USAR_SYNCHRONIZED) {
            // synchronized (this): trava o próprio objeto da Central
            synchronized (this) {
                // sorteia o número dentro da trava
                return sortearNumero();
            }
        }
        // chave desligada: qualquer thread entra a qualquer momento (sem proteção)
        return sortearNumero();
    }

    // NOVO (Parte 4): o antigo "return proximoNumero++" separado em 3 passos
    private int sortearNumero() {
        // passo 1: LÊ o valor atual do contador
        int numero = proximoNumero;
        // pausa curta: outra thread pode ler o MESMO valor antes de esta gravar
        pausar(PAUSA_CORRIDA_MS);
        // passo 2: GRAVA o próximo valor (se outra thread leu o mesmo, uma soma se perde)
        proximoNumero = numero + 1;
        // passo 3: devolve o número lido no passo 1
        return numero;
    }

    // NOVO (Parte 4): espera o tempo pedido, em milissegundos
    private void pausar(long milissegundos) {
        // 0 ou menos: não espera nada
        if (milissegundos <= 0) {
            return;
        }
        try {
            // deixa esta thread parada pelo tempo pedido
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            // se a thread foi interrompida, devolve o aviso de interrupção e segue
            Thread.currentThread().interrupt();
        }
    }

    // Lista das senhas emitidas, compartilhada por todos os totens
    // "final" = a variável sempre aponta para esta mesma lista
    private final List<Senha> emitidas = new ArrayList<>();

    // Adiciona uma senha à lista
    // synchronized porque ArrayList não é seguro para várias threads ao mesmo tempo
    private synchronized void guardar(Senha senha) {
        // coloca a senha no fim da lista
        emitidas.add(senha);
    }

    // Devolve uma cópia da lista
    // (quem lê trabalha na cópia, sem mexer na lista original)
    private synchronized List<Senha> copiarEmitidas() {
        // cria uma nova lista com os mesmos elementos
        return new ArrayList<>(emitidas);
    }

    // @Override: este método implementa o rpc PegarSenha do .proto
    @Override
    public void pegarSenha(PedidoSenha req, StreamObserver<Senha> observador) {
        // NOVO (Parte 4): atraso do Teste 1, FORA do synchronized de propósito
        // (se ficasse dentro, os pedidos esperariam um pelo outro e o assíncrono não ganharia tempo)
        pausar(ATRASO_MS);
        // pega o próximo número do contador
        int numero = gerarNumero();
        // prefixo "P" para prioritário e "N" para normal
        String prefixo = req.getTipo() == TipoAtendimento.PRIORITARIO
                ? "P" : "N";
        // monta o código, por exemplo N-001 (3 dígitos com zeros à esquerda)
        String codigo = String.format("%s-%03d", prefixo, numero);
        // texto de confirmação para a pessoa
        String mensagem = req.getNome() + ", sua senha é " + codigo
                + " (totem: " + req.getLocal() + ")";
        // registra no console da Central
        System.out.println("Senha " + codigo + " para " + req.getNome()
                + " no totem " + req.getLocal());
        // monta a mensagem Senha usando o builder gerado pelo protoc
        Senha resposta = Senha.newBuilder()
                .setNumero(numero)     // preenche o campo numero
                .setCodigo(codigo)     // preenche o campo codigo
                .setMensagem(mensagem) // preenche o campo mensagem
                .build();              // finaliza a mensagem (ela fica imutável)
        // registra a senha na lista de emitidas
        guardar(resposta);
        observador.onNext(resposta); // envia a resposta ao totem
        observador.onCompleted();    // encerra a chamada
    }

    // @Override porque implementa o rpc ListarSenhas do .proto
    // req é o Vazio que chega (não usamos); observador devolve a resposta
    @Override
    public void listarSenhas(Vazio req, StreamObserver<ListaSenhas> observador) {
        // começa a montar a mensagem de resposta
        ListaSenhas resposta = ListaSenhas.newBuilder()
                // coloca todas as senhas da cópia no campo repeated "senhas"
                .addAllSenhas(copiarEmitidas())
                // finaliza a mensagem
                .build();
        observador.onNext(resposta); // envia a lista ao totem
        observador.onCompleted();    // encerra a chamada
    }
}