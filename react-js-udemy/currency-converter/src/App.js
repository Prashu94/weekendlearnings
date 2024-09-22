// `https://api.frankfurter.app/latest?amount=100&from=EUR&to=USD`
import { useEffect, useState } from "react";
export default function App() {
    const [amount, setAmount] = useState(100);
    const [fromCurrency, setFromCurrency] = useState("USD");
    const [toCurrency, setToCurrency] = useState("INR");
    const [isLoading, setIsLoading] = useState(false);
    const [convertedAmount, setConvertedAmount] = useState(0);
    

    useEffect(function(){
        async function convert(){
            setIsLoading(true);
            const res = await fetch(`https://api.frankfurter.app/latest?amount=${amount}&from=${fromCurrency}&to=${toCurrency}`);
            const data = await res.json();
            setConvertedAmount(data.rates[toCurrency]);
            setIsLoading(false);    
        }

        if (fromCurrency === toCurrency){
            return setConvertedAmount(amount);
        }
        convert();
    }, [amount, fromCurrency, toCurrency]);
    return (
      <div>
        <input type="text" value = {amount} onChange = {(e) => setAmount(e.target.value)} disabled={isLoading}/>
        <select value = {fromCurrency} onChange={(e) => setFromCurrency(e.target.value)} disabled={isLoading}>
          <option value="USD">USD</option>
          <option value="EUR">EUR</option>
          <option value="CAD">CAD</option>
          <option value="INR">INR</option>
        </select>
        <select value = {toCurrency} onChange={(e) => setToCurrency(e.target.value)} disabled={isLoading}>
          <option value="USD">USD</option>
          <option value="EUR">EUR</option>
          <option value="CAD">CAD</option>
          <option value="INR">INR</option>
        </select>
        <p>
            {convertedAmount} {toCurrency}
        </p>
      </div>
    );
  }
  