import { setConstantValue } from "typescript";
import CardComponent, { CardState } from "./CardComponent";
import { useState } from "react";

interface GameWindowProps {
  quantity: number;
}

function GameWindow(props: GameWindowProps) {
  console.log("pwet");
  const result = [];
  let qt: number = props.quantity;
  const [visibleCount, setVisibleCount] = useState<number>(0);

  // const [val, setVal] = useState<CardState>("hidden");

  function incrementCount() {
    setVisibleCount(visibleCount + 1);
  }

  function reveal() {
    if (visibleCount >= 2) {
      //too many revealed
    } else {
    }
    // setVal("visible");
  }

  while (qt > 0) {
    //prepare new card ?
    result.push(
      <CardComponent
        cardState="hidden"
        visibleCount={visibleCount}
        incrementCount={incrementCount}
        reveal={reveal}
      />
    );
    result.push(
      <CardComponent
        cardState="hidden"
        visibleCount={visibleCount}
        incrementCount={incrementCount}
        reveal={reveal}
      />
    );
    qt--;
  }

  return <section className="game-window"> {result}</section>;
}

export default GameWindow;
