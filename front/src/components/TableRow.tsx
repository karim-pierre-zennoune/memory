function TableRow(props: any) {

    return (<tr>
        <td>{props.index + 1}</td>
        {props.columns == 4 ? <td>{props.owner}</td> : <></>}
        <td>{props.score}</td>
        <td>{props.date}</td>
    </tr>);


}

export default TableRow;