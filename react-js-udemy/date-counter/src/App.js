import { useState } from "react";
import "./styles.css";

function Counter() {
  const [step, setStep] = useState(1);
  const [counter, setCounter] = useState(0);
  const date = new Date();
  date.setDate(date.getDate() + counter);
  return (
    <div>
      <div>
        <button onClick={() => setStep((s) => s - 1)}>-</button>
        <span>Step: {step}</span>
        <button onClick={() => setStep((s) => s + 1)}>+</button>
      </div>

      <div>
        <button onClick={() => setCounter((c) => c - step)}>-</button>
        <span>Count: {counter}</span>
        <button onClick={() => setCounter((c) => c + step)}>+</button>
      </div>

      <p>
        <span>
          {counter == 0
            ? "Today is "
            : counter > 0
            ? `${counter} days from today `
            : `${Math.abs(counter)} days ago was `}
        </span>
        <span>{date.toDateString()}</span>
      </p>
    </div>
  );
}
export default function App() {
  return (
    <div className="App">
      <Counter />
    </div>
  );
}
