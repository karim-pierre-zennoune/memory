import { useState } from "react";

function NumberPicker() {
  const [val, setVal] = useState(6);

  function increment() {
    if (val < 12) {
      setVal(val + 1);
    }
  }

  function decrement() {
    if (val > 3) {
      setVal(val - 1);
    }
  }

  return (
    <div className="number-picker">
      <p>Number of Cards</p>
      <p>
        <span onClick={decrement}>◀</span> {val}{" "}
        <span onClick={increment}>▶</span>
      </p>
      <button>Play</button>
    </div>
  );
}

export default NumberPicker;
