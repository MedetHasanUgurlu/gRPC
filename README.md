

int int32 \
long int64 \
float float \
double double \
boolean bool \
String string \
byte[] bytes\


`message Person{
    string name =1;
    int32 age = 2;
    repeated Car car;
}`   


`Person sam  = Person.newBuilder()
                .setName("Sam")
                .setAge("26")
                .addCar(volvo)
                .addCar(BMW)
                .build();`


* THERE IS NO NULL IN PROTOBUF.

**Default value** 

ANY NUMBER TYPE default value 0\
bool false \
string empty  string \
enum first value \
repeated empty list \
map wrapper empty map \



## unary gRPC 

### .proto
    service BankService{
        rpc getBalance(BalanceCheckRequest) returns (Balance);
    }
### .server
    final Server server = ServerBuilder.forPort(6565).addService(new BankService()).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

### .client
    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
    final BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    final MoneyResponse moneyResponse = bankServiceBlockingStub.drawMoney(MoneyRequest.newBuilder().setAmount(566).build());
    System.out.println(moneyResponse.getAccount());

## serverside streaming gRPC
    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
    final BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    bankServiceBlockingStub.withDraw(MoneyRequest.newBuilder().setAmount(56).build()).forEachRemaining(moneyResponse1 -> System.out.println(moneyResponse1.getAccount()));

### .proto
    service BankService{
        rpc withDraw(MoneyRequest) returns (stream MoneyRequest);
    }
### .server
    final Server server = ServerBuilder.forPort(6565).addService(new BankService()).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

### .client
#### sync client
    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
    final BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    final MoneyResponse moneyResponse = bankServiceBlockingStub.drawMoney(MoneyRequest.newBuilder().setAmount(566).build());
    System.out.println(moneyResponse.getAccount());

#### async client

    final BankServiceGrpc.BankServiceStub bankServiceNonStub = BankServiceGrpc.newStub(channel);
    bankServiceNonStub.withDraw(MoneyRequest.newBuilder().setAmount(56).build(),new MoneyStreamResponse());

### Deadline
Balance balance = blockingStub.withDeadline(Deadline.after(2,TımeUnit.SECONDS)).getBalance(request);



'
    public class DeadlineInterceptor implements ClieentInterceptor{
        @Override
        public<ReqT,RespT> ClientCall<ReqT, ReespT> incerceptCall(MethodDescriptor<ReqT, RespT> methodDesriptor, CallOptions callOptions, Channel channel){
            return channel.newCall(methodDescriptor, callOptions.withDeadLine(Deadline.after(4,TimeUnit.SECONDS)));
        
        }
    }


    .SERVER

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",6565).intercept(new DeadlineInterceptor()).usePlaintext().build();


Interceptor\
Attaching ClientService Token via Metadata


    ClientConstants.java\
    private static final Metadata METADATA = new Metadata();
    static{
        METADATA.put(Metadata.Key.of("client-token",Metadata.ASCII_STRING_MARSHALLER),"bacnk-client-secret");
    }
    public static Metadata getClientToken(){
        return METADATA;
    }
    
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",6565)
    .intercept(MetadataUtils.newAttachHeadersInterceptor(ClientConstants.getClientToken())).usePlaintext().build();

    


'
























