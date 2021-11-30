package au.com.dmg.fusion.request.paymentrequest;

import org.junit.Test;
import au.com.dmg.fusion.request.paymentrequest.CardReq;

public class CryptoReqTest {

    @Test
    public void testJson() {
        CryptoReq cryptoRequest = new CryptoReq.Builder()
                .currency("BTC")
                .requestedAmount(new BigDecimal("1.0"))
                .senderAddress("testSenderAddress")

                .build();
    }
}
