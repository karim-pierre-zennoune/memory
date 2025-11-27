import Card, { CardState } from "../classes/Card";

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
