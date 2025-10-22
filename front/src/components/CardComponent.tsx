import { useState } from "react";

export type CardState = "hidden" | "visible" | "paired";

interface CardProps {
  cardState: CardState;
  visibleCount: number;
  incrementCount: Function;
  reveal: () => void;
}

function CardComponent(props: CardProps) {
  // const [val, setVal] = useState<CardState>("hidden");

  // function reveal() {
  //   setVal("visible");
  // }

  if (props.cardState == "hidden") {
    return (
      <img
        className="card"
        onClick={props.reveal}
        src="/assets/img/cards/card_back_red.png"
        alt=""
      />
    );
  } else if (props.cardState == "visible") {
    return (
      <img
        className="card"
        src="/assets/img/cards/queen_of_hearts2.png"
        alt=""
      />
    );
  } else {
    //if paired, todo change css to grey paired card out
    return (
      <img
        className="card"
        src="/assets/img/cards/queen_of_hearts2.png"
        alt=""
      />
    );
  }
}

export default CardComponent;
