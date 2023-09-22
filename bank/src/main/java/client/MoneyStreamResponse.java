package client;

import com.medron.service.MoneyResponse;
import io.grpc.stub.StreamObserver;

public class MoneyStreamResponse implements StreamObserver<MoneyResponse> {
    @Override
    public void onNext(final MoneyResponse moneyResponse) {

    }

    @Override
    public void onError(final Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
