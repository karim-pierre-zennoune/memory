"use strict";
import CardComponent from "./CardComponent";
import { ReactElement, useState } from "react";
import Deck from "../classes/Deck";
import Card from "../classes/Card";
import { GameState } from "./Menu";

interface GameWindowProps {
  quantity: number;
  gameState: GameState;
  goScore: Function;
}

function GameWindow(props: GameWindowProps) {
  const [deck, setDeck] = useState<Deck>(new Deck(props.quantity));
  const [forceRerender, setForceRerender] = useState(0);
  let cards: Array<Card> = deck.getDeck();
  let score: number = 0;

  function handleCardClic() {
    if (deck.checkGameEnd()) {
      props.goScore();
    }
    setForceRerender(forceRerender + 1);
  }

  if (props.gameState === GameState.Score) {
    return (
      <section className="game-window"> You Win score is : {score}</section>
    );
  } else {
    return (
      <section className="game-window">
        {cards.map((card) => {
          return (
            <CardComponent
              key={card.getId()}
              card={card}
              handleCardClic={handleCardClic}
            />
          );
        })}
      </section>
    );
  }
}

export default GameWindow;
