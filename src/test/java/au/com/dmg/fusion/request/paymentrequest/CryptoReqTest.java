/*
 * Copyright (c) 2021. DatameshGroup
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package au.com.dmg.fusion.request.paymentrequest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.math.BigDecimal;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import au.com.dmg.fusion.util.InstantAdapter;
import au.com.dmg.fusion.util.BigDecimalAdapter;

public class CryptoReqTest {

    @Test
    public void testJson() {
        CryptoReq cryptoRequest = new CryptoReq.Builder()
                .currency("BTC")
                .requestedAmount(new BigDecimal("1.0"))
                .senderAddress("testSenderAddress")
                .build();

        assert (cryptoRequest.getCurrency().equals("BTC"));
        assert (cryptoRequest.getRequestedAmount().equals(new BigDecimal("1.0")));
        assert (cryptoRequest.getSenderAddress().equals("testSenderAddress"));

        Moshi moshi = new Moshi.Builder()
            .add(new BigDecimalAdapter())
            .add(new InstantAdapter())
            .build();
        
        JsonAdapter<CryptoReq> jsonAdapter = moshi.adapter(CryptoReq.class);
        String json = jsonAdapter.toJson(cryptoRequest);
        
        CryptoReq serializedCryptoRequest = null;
        try {
            serializedCryptoRequest = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (serializedCryptoRequest.getCurrency().equals("BTC"));
        assert (serializedCryptoRequest.getRequestedAmount().equals(new BigDecimal("1.0")));
        assert (serializedCryptoRequest.getSenderAddress().equals("testSenderAddress"));
    }

    @Test
    public void testNull() {
        try {
            CryptoReq cryptoRequest = new CryptoReq.Builder()
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullCurrency() {
        try {
            CryptoReq cryptoRequest = new CryptoReq.Builder()
                .requestedAmount(new BigDecimal("1.0"))
                .senderAddress("testSenderAddress")
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullSenderAddress() {
        try {
            CryptoReq cryptoRequest = new CryptoReq.Builder()
                .currency("ETH")
                .requestedAmount(new BigDecimal("1.0"))
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullRequestedAmount() {
        try {
            CryptoReq cryptoRequest = new CryptoReq.Builder()
                .currency("XMR")
                .senderAddress("testSenderAddress")
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }
}
