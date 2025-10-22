import { useState } from "react";
import GameWindow from "./GameWindow";

function Menu() {
  const [val, setVal] = useState<number>(6);

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

  function play() {
    //todo ?
    // Deck constructor ?
  }

  return (
    <section className="wrapper">
      <div className="number-picker">
        <p>Number of Pairs</p>
        <p>
          <span onClick={decrement}>◀</span> {val}{" "}
          <span onClick={increment}>▶</span>
        </p>
        <button onClick={play}>Play</button>
        <GameWindow quantity={val} />
      </div>
    </section>
  );
}

export default Menu;
