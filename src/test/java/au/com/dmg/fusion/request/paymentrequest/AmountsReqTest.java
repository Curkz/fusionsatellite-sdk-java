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

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import au.com.dmg.fusion.data.CurrencyType;
import au.com.dmg.fusion.data.PaymentBrand;
import au.com.dmg.fusion.data.PaymentType;
import au.com.dmg.fusion.data.UnitOfMeasure;
import au.com.dmg.fusion.util.BigDecimalAdapter;
import au.com.dmg.fusion.util.InstantAdapter;

public class AmountsReqTest {

    @Test
    public void testCardReqJson() {        
        AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Card)
            .cardReq(new CardReq.Builder()
                    .currency("AUD")
                    .requestedAmount(new BigDecimal("5.0"))
                    .build()
            )
            .build();
        assert (amountsReq.getType().equals(CurrencyType.Card));
        assert (amountsReq.getCardReq().getCurrency().equals("AUD"));
        assert (amountsReq.getCardReq().getRequestedAmount().equals(new BigDecimal("5.0")));

        Moshi moshi = new Moshi.Builder()
            .add(new BigDecimalAdapter())
            .add(new InstantAdapter())
            .build();
        
        JsonAdapter<AmountsReq> jsonAdapter = moshi.adapter(AmountsReq.class);

        String json = jsonAdapter.toJson(amountsReq);

        AmountsReq serializedCardRequest = null;
        try {
            serializedCardRequest = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (serializedCardRequest.getType().equals(CurrencyType.Card));
        assert (serializedCardRequest.getCardReq().getCurrency().equals("AUD"));
        assert (serializedCardRequest.getCardReq().getRequestedAmount().equals(new BigDecimal("5.0")));
    }

    @Test
    public void testCryptoReqJson() {        
        AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Crypto)
            .cryptoReq(new CryptoReq.Builder()
                    .currency("BTC")
                    .requestedAmount(new BigDecimal("1.0"))
                    .senderAddress("testSenderAddress")
                    .build()
            )
            .build();
        assert (amountsReq.getType().equals(CurrencyType.Crypto));
        assert (amountsReq.getCryptoReq().getCurrency().equals("BTC"));
        assert (amountsReq.getCryptoReq().getRequestedAmount().equals(new BigDecimal("1.0")));
        assert (amountsReq.getCryptoReq().getSenderAddress().equals("testSenderAddress"));

        Moshi moshi = new Moshi.Builder()
            .add(new BigDecimalAdapter())
            .add(new InstantAdapter())
            .build();
        
        JsonAdapter<AmountsReq> jsonAdapter = moshi.adapter(AmountsReq.class);

        String json = jsonAdapter.toJson(amountsReq);

        AmountsReq serializedCardRequest = null;
        try {
            serializedCardRequest = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (serializedCardRequest.getType().equals(CurrencyType.Crypto));
        assert (serializedCardRequest.getCryptoReq().getCurrency().equals("BTC"));
        assert (serializedCardRequest.getCryptoReq().getRequestedAmount().equals(new BigDecimal("1.0")));
    }

    @Test
    public void testNullType() {
        try {
            AmountsReq amountsReq = new AmountsReq.Builder()
            .cardReq(new CardReq.Builder()
                    .currency("AUD")
                    .requestedAmount(new BigDecimal("5.0"))
                    .build()
            )
            .build();
        } catch(Exception e) {

        }
    }

    @Test
    public void testBothReqJson() {
        try {
            AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Crypto)
            .cardReq(new CardReq.Builder()
                    .currency("AUD")
                    .requestedAmount(new BigDecimal("5.0"))
                    .build()
            )
            .cryptoReq(new CryptoReq.Builder()
                    .currency("BTC")
                    .requestedAmount(new BigDecimal("1.0"))
                    .senderAddress("testSenderAddress")
                    .build()
            )
            .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullReqJson() {
        try {
            AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Crypto)
            .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testMismatchType() {
        try {
            AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Crypto)
            .cardReq(new CardReq.Builder()
                    .currency("AUD")
                    .requestedAmount(new BigDecimal("5.0"))
                    .build()
            )
            .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }

        try {
            AmountsReq amountsReq = new AmountsReq.Builder()
            .type(CurrencyType.Card)
            .cryptoReq(new CryptoReq.Builder()
                    .currency("BTC")
                    .requestedAmount(new BigDecimal("1.0"))
                    .senderAddress("testSenderAddress")
                    .build()
            )
            .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

}
