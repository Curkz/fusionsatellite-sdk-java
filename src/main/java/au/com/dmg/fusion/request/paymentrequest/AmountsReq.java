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

import au.com.dmg.fusion.data.CurrencyType;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

// import javax.smartcardio.Card;

public class AmountsReq {

    @Json(name = "CurrencyType")
    private final CurrencyType type;
    @Json(name = "CardReq")
    private final CardReq cardReq;
    @Json(name = "CryptoReq")
    private final CryptoReq cryptoReq;

    public CurrencyType getType() {
        return type;
    }

    public CardReq getCardReq() {
        return cardReq;
    }

    public CryptoReq getCryptoReq() {
        return cryptoReq;
    }

    public static class Builder {

        private CurrencyType type;
        private CardReq cardReq;
        private CryptoReq cryptoReq;

        public Builder() {
        }

        Builder(CurrencyType type, CardReq cardReq, CryptoReq cryptoReq) {
            this.type = type;
            this.cardReq = cardReq;
            this.cryptoReq = cryptoReq;
        }

        public Builder type(CurrencyType type) {
            this.type = type;
            return Builder.this;
        }

        @Nullable
        public Builder cardReq(CardReq cardReq) {
            this.cardReq = cardReq;
            return Builder.this;
        }

        @Nullable
        public Builder cryptoReq(CryptoReq cryptoReq) {
            this.cryptoReq = cryptoReq;
            return Builder.this;
        }

        public AmountsReq build() {
            if (this.type == null) {
                throw new NullPointerException("The property \"type\" is null. "
                        + "Please set the value by \"type()\". "
                        + "The properties \"type\", \"requestedAmount\" are required.");
            }
            if (this.cryptoReq == null && this.cardReq == null) {
                throw new NullPointerException("The properties \"cardReq\" and \"cryptoReq\" are null. "
                        + "Please set the value of one by \"cardReq() or \"cryptoReq()\"\". "
                        + "Only one of either properties \"cardReq\" or \"cryptoReq\" are required.");
            }
            if (this.cryptoReq != null && this.cardReq != null) {
                throw new NullPointerException("The properties \"cardReq\" and \"cryptoReq\" are both NOT null. "
                        + "Please set one to null by \"cardReq() or \"cryptoReq()\"\". "
                        + "Only one of either properties \"cardReq\" or \"cryptoReq\" is required.");
            }
            if (this.type == CurrencyType.Card && this.cardReq == null) {
                throw new NullPointerException("The property \"type\" is Card but property cardReq is null. "
                + "Please set cardReq by \"cardReq()\". "
                + "The \"type\" property, must match the given currency type");
            }
            if (this.type == CurrencyType.Crypto && this.cryptoReq == null) {
                throw new NullPointerException("The property \"type\" is Crypto but property cryptoReq is null. "
                + "Please set cryptoReq by \"cryptoReq()\". "
                + "The \"type\" property, must match the given currency type");
            }
            return new AmountsReq(this);
        }
    }

    private AmountsReq(Builder builder) {
        this.type = builder.type;
        this.cardReq = builder.cardReq;
        this.cryptoReq = builder.cryptoReq;
    }
}



/*
package au.com.dmg.fusionsatellite.PaymentRequest
class AmountsReq
@Required String currency
@Required BigDecimal requestedAmount
BigDecimal cashBackAmount
BigDecimal tipAmount
BigDecimal paidAmount
BigDecimal maximumCashBackAmount
BigDecimal minimumSplitAmount
* */