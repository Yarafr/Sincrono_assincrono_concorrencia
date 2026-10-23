// Pacote do projeto do Cliente (o mesmo da TelaTotem e do TotemAssincrono)
package com.mycompany.clienteatendimentorpc;

// Classe gerada pelo protoc: contém os stubs do serviço
import com.mycompany.atendimentorpc.AtendimentoServiceGrpc;
// Mensagem que o totem envia ao pedir uma senha
import com.mycompany.atendimentorpc.PedidoSenha;
// Mensagem que a Central devolve com a senha
import com.mycompany.atendimentorpc.Senha;
// Enum NORMAL / PRIORITARIO
import com.mycompany.atendimentorpc.TipoAtendimento;
// Canal = a conexão com a Central
import io.grpc.ManagedChannel;
// Ajuda a criar o canal
import io.grpc.ManagedChannelBuilder;

// Totem de comparação: pede as senhas uma de cada vez, com o stub SÍNCRONO (bloqueante)
public class TotemSincrono {

    // Quantidade de pedidos do Teste 1 (3 pedidos x 3 s de atraso = cerca de 9 s)
    private static final int TOTAL = 3;

    public static void main(String[] args) {
        // Abre a conexão com a Central (a mesma porta do Servidor)
        ManagedChannel canal = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // sem TLS: só no laboratório
                .build();

        // Stub SÍNCRONO: cada chamada espera a resposta chegar antes de continuar
        AtendimentoServiceGrpc.AtendimentoServiceBlockingStub bloqueante =
                AtendimentoServiceGrpc.newBlockingStub(canal);

        // Guarda o instante de início para medir o tempo
        long inicio = System.currentTimeMillis();

        // Repete TOTAL vezes, UM pedido depois do outro
        for (int i = 1; i <= TOTAL; i++) {
            // Monta o pedido (cada um com um nome diferente)
            PedidoSenha pedido = PedidoSenha.newBuilder()
                    .setNome("Cliente " + i)            // nome da pessoa
                    .setTipo(TipoAtendimento.NORMAL)    // atendimento normal
                    .setLocal("Teste sincrono")         // identifica este totem
                    .build();                           // finaliza a mensagem

            // BLOQUEIA a thread aqui até a Central responder
            Senha senha = bloqueante.pegarSenha(pedido);

            // Mostra a senha recebida e quanto tempo já passou desde o início
            System.out.println("Recebi " + senha.getCodigo() + " apos "
                    + (System.currentTimeMillis() - inicio) + " ms");
        }

        // Tempo total de todos os pedidos
        System.out.println("Pedidos: " + TOTAL);
        System.out.println("Tempo total: "
                + (System.currentTimeMillis() - inicio) + " ms");

        // Fecha a conexão para o programa poder terminar
        canal.shutdown();
    }
}
