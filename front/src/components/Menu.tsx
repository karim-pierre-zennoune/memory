import { useState } from "react";
import GameWindow from "./GameWindow";

export enum GameState {
  Menu,
  Play,
  Score,
}

function Menu() {
  const [val, setVal] = useState<number>(6);

  const [gameState, setGameState] = useState<GameState>(GameState.Menu);
  // const [playing, setPlaying] = useState<boolean>(false);

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

  function goPlay() {
    // setPlaying(true);
    setGameState(GameState.Play);
  }

  function goMenu() {
    setGameState(GameState.Menu);
  }

  function goScore() {
    setGameState(GameState.Score);
  }

  {
    if (gameState === GameState.Menu) {
      return (
        <section className="wrapper">
          <div className="number-picker">
            <p>Number of Pairs</p>
            <p>
              <span onClick={decrement}>◀</span> {val}{" "}
              <span onClick={increment}>▶</span>
            </p>
            <button onClick={goPlay}>Start Game</button>
          </div>
        </section>
      );
    } else if (gameState === GameState.Play) {
      return (
        <section className="wrapper">
          <div className="number-picker">
            <button onClick={goMenu}>Forfeit</button>
            <GameWindow
              quantity={val}
              gameState={gameState}
              goScore={goScore}
            />
          </div>
        </section>
      );
    } else {
      return (
        <section className="wrapper">
          <div className="number-picker">
            <p>Number of Pairs</p>
            <p>
              <span onClick={decrement}>◀</span> {val}{" "}
              <span onClick={increment}>▶</span>
            </p>
            <button onClick={goPlay}>Start Game</button>
            <GameWindow quantity={0} gameState={gameState} goScore={goScore} />
          </div>
        </section>
      );
    }
  }
}

export default Menu;
