import { useState } from "react";
import Card, { CardState } from "../classes/Card";
import Deck from "../classes/Deck";

interface CardProps {
  key: number;
  card: Card;
  handleCardClic: Function;
}

function CardComponent(props: CardProps) {
  return (
    <img
      className={
        props.card.getState() === CardState.Paired ? "card paired-fade" : "card"
      }
      onClick={() => {
        props.card.handleClick();
        props.handleCardClic();
      }}
      src={props.card.getImagePath()}
      alt=""
    />
  );
}

export default CardComponent;
