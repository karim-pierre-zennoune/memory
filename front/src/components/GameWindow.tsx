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

interface ScoreDtoForInsert {
  score: number;
  ownerId: number;
  date: Date;
}

function GameWindow(props: GameWindowProps) {
  const [deck, setDeck] = useState<Deck>(new Deck(props.quantity));
  const [forceRerender, setForceRerender] = useState(0);
  const [error, setError] = useState(null);


  let cards: Array<Card> = deck.getDeck();
  // let score: number = 0;
  let score: number = Math.round(Math.random() * 10000);

  function handleCardClic() {
    if (deck.checkGameEnd()) {
      props.goScore();
    }
    setForceRerender(forceRerender + 1);
  }

  if (props.gameState === GameState.Score) {
    if (sessionStorage.getItem("id")) {
      let scoreDto: ScoreDtoForInsert = {
        score: score,
        ownerId: Number(sessionStorage.getItem("id")),
        date: new Date()
      }
      //todo post score
      fetch("http://localhost:8080/addscore", {
        method: "POST",
        body: JSON.stringify(scoreDto),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          if (!res.ok) { console.log("unable to save score"); }
          else {

            console.log("register OK");

          }
          // return res.json();
        })
        .catch((err) => {
          setError(err.message);
          console.log(err.message);
        })



    }

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
