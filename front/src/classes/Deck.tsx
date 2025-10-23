import CardNames from "../CardNames";
import Card from "./Card";

class Deck {
  private deck: Array<Card> = new Array<Card>();
  private unpaired: number;
  private currentVisible: Array<Card> = new Array<Card>();

  public constructor(pairsQuantity: number) {
    this.unpaired = pairsQuantity;

    for (let i: number = 0; i < pairsQuantity; i++) {
      this.deck.push(new Card(CardNames[i], i * 2, i * 2 + 1, this));
      this.deck.push(new Card(CardNames[i], i * 2 + 1, i * 2, this));
    }
    this.deck = this.shuffle(this.deck);
  }

  public getDeck(): Array<Card> {
    return this.deck;
  }

  public getCurrentVisibleCount(): number {
    return this.currentVisible.length;
  }

  public addVisible(elem: Card): void {
    this.currentVisible.push(elem);
  }

  public checkGameEnd(): boolean {
    if (this.unpaired === 0) {
      //game ends. do things
      console.log("win");
      return true;
    }
    return false;
  }

  public shuffle(arr: Array<Card>) {
    let currentIndex = arr.length;

    while (currentIndex != 0) {
      let randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;
      [arr[currentIndex], arr[randomIndex]] = [
        arr[randomIndex],
        arr[currentIndex],
      ];
    }
    return arr;
  }

  public checkPair(): void {
    if (
      this.currentVisible[0].getId() === this.currentVisible[1].getPairedId()
    ) {
      this.currentVisible[0].setPaired();
      this.currentVisible[1].setPaired();
      this.unpaired--;
      this.currentVisible.pop();
      this.currentVisible.pop();
      // this.checkGameEnd();
    } else {
      setTimeout(() => {
        this.currentVisible[0].setHidden();
        this.currentVisible[1].setHidden();
        this.currentVisible.pop();
        this.currentVisible.pop();
      }, 500);
    }
  }
}

export default Deck;
