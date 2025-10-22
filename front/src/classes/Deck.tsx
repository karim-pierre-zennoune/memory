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
  }

  public getCurrentVisibleCount(): number {
    return this.currentVisible.length;
  }

  public addVisible(elem: Card): void {
    this.currentVisible.push(elem);
  }

  public checkGameEnd(): void {
    if (this.unpaired === 0) {
      //game ends. do things
    }
  }

  public checkPair(): void {
    if (
      this.currentVisible[0].getId() === this.currentVisible[1].getPairedId()
    ) {
      this.currentVisible[0].setPaired();
      this.currentVisible[1].setPaired();
      this.unpaired--;
    } else {
      this.currentVisible[0].setHidden();
      this.currentVisible[1].setHidden();
    }
    this.currentVisible.pop();
    this.currentVisible.pop();
  }
}

export default Deck;
