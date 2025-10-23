import { setConstantValue } from "typescript";
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
  // const [isFinished, setIsFinished] = useState(false);

  let cards: Array<Card> = deck.getDeck();
  let board: any[] = [];
  let score = 0;

  function handleCardClic() {
    if (deck.checkGameEnd()) {
      // setIsFinished(true);
      props.goScore();
    }

    setForceRerender(forceRerender + 1);
    // setDeck(deck);
  }

  cards.forEach((card) => {
    board.push(
      <CardComponent
        key={card.getId()}
        card={card}
        handleCardClic={handleCardClic}
      />
    );
  });

  if (props.gameState === GameState.Score) {
    return (
      <section className="game-window"> You Win score is : {score}</section>
    );
  } else {
    return <section className="game-window"> {board}</section>;
  }
}

export default GameWindow;
