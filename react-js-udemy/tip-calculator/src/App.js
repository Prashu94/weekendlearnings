import React from 'react';
import './styles.css';

function App() {
  return (
    <div>
      <TipCalculator/>
    </div>
  );
}

function TipCalculator(){
  const [billAmount, setBillAmount] = React.useState("");
  const [percentage1, setPercentage1] = React.useState(0);
  const [percentage2, setPercentage2] = React.useState(0);

  // derived state
  const tipAmount1 = billAmount * ((percentage1 + percentage2) / 2 / 100);
  

  function handleReset(){
    setBillAmount("");
    setPercentage1(0);
    setPercentage2(0);
  }

  return(
    <div>
      <BillInput billAmount={billAmount} setBillAmount={setBillAmount}/>
      <SelectPercentage percentage={percentage1} setPercentage={setPercentage1}>
        How did you like the service?
      </SelectPercentage>
      <SelectPercentage percentage={percentage2} setPercentage={setPercentage2}>
        How did your friend like the service?
      </SelectPercentage>

      {
        billAmount > 0 && (
          <>
            <Output bill={billAmount} tip={tipAmount1}/>
            <button onClick={handleReset}>Reset</button>
          </>
        )
      }
    </div>
  )
}

function BillInput({billAmount, setBillAmount}){
  return (
    <div>
      <label>How much was the bill?</label>
      <span><input type="text" value={billAmount} onChange={(e) => setBillAmount(Number(e.target.value))}/></span>
    </div>
  )
}

function SelectPercentage({percentage, setPercentage, children}){
  return (
    <div>
      <label>{children}</label>
      <span><select value={percentage} onChange={(e) => setPercentage(Number(e.target.value))}>
          <option value="0">0%</option>
          <option value="5">5%</option>
          <option value="10">10%</option>
          <option value="15">15%</option>
        </select></span>
    </div>);
}

function Output({bill, tip}){
  return (
    <h3>
      You pay ${bill + tip} (${bill} + ${tip} tip)
    </h3>
  )
}
export default App;
