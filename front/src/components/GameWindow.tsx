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
  console.log("GameWindow Component");
  const [deck, setDeck] = useState<Deck>(new Deck(props.quantity));
  const [forceRerender, setForceRerender] = useState(0);

  let board: any[] = [];

  let cards: Array<Card> = deck.getDeck();
  let score: number = 0;

  function handleCardClic() {
    if (deck.checkGameEnd()) {
      props.goScore();
      // setDeck(new Deck(props.quantity));
    }
    setForceRerender(forceRerender + 1);
  }

  // cards.forEach((card) => {
  //   board.push(
  //     <CardComponent
  //       key={card.getId()}
  //       card={card}
  //       handleCardClic={handleCardClic}
  //     />
  //   );
  // });

  if (props.gameState === GameState.Score) {
    return (
      <section className="game-window"> You Win score is : {score}</section>
    );
  } else {
    return (
      <section className="game-window">
        {/* {board} */}
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
