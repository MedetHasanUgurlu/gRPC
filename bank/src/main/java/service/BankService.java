package service;

import com.medron.service.BankServiceGrpc;
import com.medron.service.MoneyRequest;
import com.medron.service.MoneyResponse;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {


    @Override
    public void drawMoney(final MoneyRequest request, final StreamObserver<MoneyResponse> responseObserver) {
        final int amount = request.getAmount();
        final MoneyResponse moneyResponse = MoneyResponse.newBuilder().setAccount(75632*amount).build();
        responseObserver.onNext(moneyResponse);
        responseObserver.onCompleted();
    }
}
