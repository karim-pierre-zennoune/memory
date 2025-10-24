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

  function increment(): void {
    if (val < 12) {
      setVal(val + 1);
    }
  }

  function decrement(): void {
    if (val > 3) {
      setVal(val - 1);
    }
  }

  function goPlay(): void {
    setGameState(GameState.Play);
  }

  function goMenu(): void {
    setGameState(GameState.Menu);
  }

  function goScore(): void {
    setGameState(GameState.Score);
  }

  {
    if (gameState === GameState.Menu) {
      return (
        <section className="wrapper">
          <div className="number-picker">
            <p>Number of Pairs</p>
            <p className="no-select">
              <span className="arrows" onClick={decrement}>
                ◀
              </span>{" "}
              {val}{" "}
              <span className="arrows" onClick={increment}>
                ▶
              </span>
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
          </div>
          <GameWindow quantity={val} gameState={gameState} goScore={goScore} />
        </section>
      );
    } else {
      console.log(val);

      return (
        <section className="wrapper">
          <GameWindow quantity={val} gameState={gameState} goScore={goScore} />
          <div className="number-picker">
            <p>Go back to menu</p>

            <button onClick={goMenu}>Menu</button>
          </div>
        </section>
      );

      // return (
      //   <section className="wrapper">
      //     <div className="number-picker">
      //       <p>Number of Pairs</p>
      //       <p className="no-select">
      //         <span className="arrows" onClick={decrement}>
      //           ◀
      //         </span>{" "}
      //         {val}{" "}
      //         <span className="arrows" onClick={increment}>
      //           ▶
      //         </span>
      //       </p>
      //       {/* <button onClick={goPlay}>Start Game</button> */}
      //       <button onClick={goPlay}>Start Game</button>
      //     </div>
      //     <GameWindow quantity={val} gameState={gameState} goScore={goScore} />
      //   </section>
      // );
    }
  }
}

export default Menu;
