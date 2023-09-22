package client;

import com.medron.service.BankServiceGrpc;
import com.medron.service.MoneyRequest;
import com.medron.service.MoneyResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BankClient {
    private static final Map<Integer, Integer> MAP = IntStream.range(1,100).boxed().collect(Collectors.toMap(Function.identity(),integer -> integer*10));

    public static void main(String[] args) {
        // Unary
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        final BankServiceGrpc.BankServiceBlockingStub bankServiceBlockingStub = BankServiceGrpc.newBlockingStub(channel);
        final MoneyResponse moneyResponse = bankServiceBlockingStub.drawMoney(MoneyRequest.newBuilder().setAmount(566).build());
        System.out.println(moneyResponse.getAccount());

        // Server Streming
        final BankServiceGrpc.BankServiceStub bankServiceNonStub = BankServiceGrpc.newStub(channel);
//        bankServiceBlockingStub.withDraw(MoneyRequest.newBuilder().setAmount(56).build()).forEachRemaining(moneyResponse1 -> System.out.println(moneyResponse1.getAccount()));
        bankServiceNonStub.withDraw(MoneyRequest.newBuilder().setAmount(56).build(),new MoneyStreamResponse());








    }
}
