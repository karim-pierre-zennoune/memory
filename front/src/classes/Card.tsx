import Deck from "./Deck";

enum CardState {
  Hidden,
  Visible,
  Paired,
}

class Card {
  private state: CardState = CardState.Hidden;
  private backPath: string = "/assets/img/cards/card_back_red.png";
  private frontPath: string;
  private id: number;
  private pairedId: number;
  private deck: Deck;

  public constructor(
    frontPath: string,
    id: number,
    pairId: number,
    deck: Deck
  ) {
    this.frontPath = "/assets/img/cards/" + frontPath;
    this.id = id;
    this.pairedId = pairId;
    this.deck = deck;
  }

  public setHidden() {
    this.state = CardState.Hidden;
  }

  public setVisible() {
    this.state = CardState.Visible;
  }

  public setPaired() {
    this.state = CardState.Paired;
  }

  public getImagePath() {
    return this.state === CardState.Hidden ? this.backPath : this.frontPath;
  }

  public getId(): number {
    return this.id;
  }

  public getPairedId(): number {
    return this.pairedId;
  }

  public handleClick() {
    if (
      this.state === CardState.Hidden &&
      this.deck.getCurrentVisibleCount() < 2
    ) {
      this.setVisible();
      this.deck.addVisible(this);

      if (this.deck.getCurrentVisibleCount() === 2) {
        this.deck.checkPair();
      }
    }
  }
}

export default Card;
