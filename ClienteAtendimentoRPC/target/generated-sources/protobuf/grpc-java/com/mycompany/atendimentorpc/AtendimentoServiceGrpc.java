package com.mycompany.atendimentorpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class AtendimentoServiceGrpc {

  private AtendimentoServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "atendimento.AtendimentoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.PedidoSenha,
      com.mycompany.atendimentorpc.Senha> getPegarSenhaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PegarSenha",
      requestType = com.mycompany.atendimentorpc.PedidoSenha.class,
      responseType = com.mycompany.atendimentorpc.Senha.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.PedidoSenha,
      com.mycompany.atendimentorpc.Senha> getPegarSenhaMethod() {
    io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.PedidoSenha, com.mycompany.atendimentorpc.Senha> getPegarSenhaMethod;
    if ((getPegarSenhaMethod = AtendimentoServiceGrpc.getPegarSenhaMethod) == null) {
      synchronized (AtendimentoServiceGrpc.class) {
        if ((getPegarSenhaMethod = AtendimentoServiceGrpc.getPegarSenhaMethod) == null) {
          AtendimentoServiceGrpc.getPegarSenhaMethod = getPegarSenhaMethod =
              io.grpc.MethodDescriptor.<com.mycompany.atendimentorpc.PedidoSenha, com.mycompany.atendimentorpc.Senha>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PegarSenha"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mycompany.atendimentorpc.PedidoSenha.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mycompany.atendimentorpc.Senha.getDefaultInstance()))
              .setSchemaDescriptor(new AtendimentoServiceMethodDescriptorSupplier("PegarSenha"))
              .build();
        }
      }
    }
    return getPegarSenhaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.Vazio,
      com.mycompany.atendimentorpc.ListaSenhas> getListarSenhasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListarSenhas",
      requestType = com.mycompany.atendimentorpc.Vazio.class,
      responseType = com.mycompany.atendimentorpc.ListaSenhas.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.Vazio,
      com.mycompany.atendimentorpc.ListaSenhas> getListarSenhasMethod() {
    io.grpc.MethodDescriptor<com.mycompany.atendimentorpc.Vazio, com.mycompany.atendimentorpc.ListaSenhas> getListarSenhasMethod;
    if ((getListarSenhasMethod = AtendimentoServiceGrpc.getListarSenhasMethod) == null) {
      synchronized (AtendimentoServiceGrpc.class) {
        if ((getListarSenhasMethod = AtendimentoServiceGrpc.getListarSenhasMethod) == null) {
          AtendimentoServiceGrpc.getListarSenhasMethod = getListarSenhasMethod =
              io.grpc.MethodDescriptor.<com.mycompany.atendimentorpc.Vazio, com.mycompany.atendimentorpc.ListaSenhas>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListarSenhas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mycompany.atendimentorpc.Vazio.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mycompany.atendimentorpc.ListaSenhas.getDefaultInstance()))
              .setSchemaDescriptor(new AtendimentoServiceMethodDescriptorSupplier("ListarSenhas"))
              .build();
        }
      }
    }
    return getListarSenhasMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AtendimentoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceStub>() {
        @java.lang.Override
        public AtendimentoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AtendimentoServiceStub(channel, callOptions);
        }
      };
    return AtendimentoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static AtendimentoServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceBlockingV2Stub>() {
        @java.lang.Override
        public AtendimentoServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AtendimentoServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return AtendimentoServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AtendimentoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceBlockingStub>() {
        @java.lang.Override
        public AtendimentoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AtendimentoServiceBlockingStub(channel, callOptions);
        }
      };
    return AtendimentoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AtendimentoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AtendimentoServiceFutureStub>() {
        @java.lang.Override
        public AtendimentoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AtendimentoServiceFutureStub(channel, callOptions);
        }
      };
    return AtendimentoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void pegarSenha(com.mycompany.atendimentorpc.PedidoSenha request,
        io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.Senha> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPegarSenhaMethod(), responseObserver);
    }

    /**
     */
    default void listarSenhas(com.mycompany.atendimentorpc.Vazio request,
        io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.ListaSenhas> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListarSenhasMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AtendimentoService.
   */
  public static abstract class AtendimentoServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AtendimentoServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AtendimentoService.
   */
  public static final class AtendimentoServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AtendimentoServiceStub> {
    private AtendimentoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtendimentoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AtendimentoServiceStub(channel, callOptions);
    }

    /**
     */
    public void pegarSenha(com.mycompany.atendimentorpc.PedidoSenha request,
        io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.Senha> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPegarSenhaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listarSenhas(com.mycompany.atendimentorpc.Vazio request,
        io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.ListaSenhas> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListarSenhasMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AtendimentoService.
   */
  public static final class AtendimentoServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<AtendimentoServiceBlockingV2Stub> {
    private AtendimentoServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtendimentoServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AtendimentoServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.mycompany.atendimentorpc.Senha pegarSenha(com.mycompany.atendimentorpc.PedidoSenha request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getPegarSenhaMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.mycompany.atendimentorpc.ListaSenhas listarSenhas(com.mycompany.atendimentorpc.Vazio request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListarSenhasMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service AtendimentoService.
   */
  public static final class AtendimentoServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AtendimentoServiceBlockingStub> {
    private AtendimentoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtendimentoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AtendimentoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.mycompany.atendimentorpc.Senha pegarSenha(com.mycompany.atendimentorpc.PedidoSenha request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPegarSenhaMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.mycompany.atendimentorpc.ListaSenhas listarSenhas(com.mycompany.atendimentorpc.Vazio request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListarSenhasMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AtendimentoService.
   */
  public static final class AtendimentoServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AtendimentoServiceFutureStub> {
    private AtendimentoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtendimentoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AtendimentoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mycompany.atendimentorpc.Senha> pegarSenha(
        com.mycompany.atendimentorpc.PedidoSenha request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPegarSenhaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mycompany.atendimentorpc.ListaSenhas> listarSenhas(
        com.mycompany.atendimentorpc.Vazio request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListarSenhasMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PEGAR_SENHA = 0;
  private static final int METHODID_LISTAR_SENHAS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PEGAR_SENHA:
          serviceImpl.pegarSenha((com.mycompany.atendimentorpc.PedidoSenha) request,
              (io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.Senha>) responseObserver);
          break;
        case METHODID_LISTAR_SENHAS:
          serviceImpl.listarSenhas((com.mycompany.atendimentorpc.Vazio) request,
              (io.grpc.stub.StreamObserver<com.mycompany.atendimentorpc.ListaSenhas>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPegarSenhaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.mycompany.atendimentorpc.PedidoSenha,
              com.mycompany.atendimentorpc.Senha>(
                service, METHODID_PEGAR_SENHA)))
        .addMethod(
          getListarSenhasMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.mycompany.atendimentorpc.Vazio,
              com.mycompany.atendimentorpc.ListaSenhas>(
                service, METHODID_LISTAR_SENHAS)))
        .build();
  }

  private static abstract class AtendimentoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AtendimentoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mycompany.atendimentorpc.Atendimento.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AtendimentoService");
    }
  }

  private static final class AtendimentoServiceFileDescriptorSupplier
      extends AtendimentoServiceBaseDescriptorSupplier {
    AtendimentoServiceFileDescriptorSupplier() {}
  }

  private static final class AtendimentoServiceMethodDescriptorSupplier
      extends AtendimentoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AtendimentoServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AtendimentoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AtendimentoServiceFileDescriptorSupplier())
              .addMethod(getPegarSenhaMethod())
              .addMethod(getListarSenhasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
