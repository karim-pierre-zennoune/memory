function TableRow(props: any) {

    return (<tr>
        <td>{props.index + 1}</td>
        <td>{props.owner}</td>
        <td>{props.score}</td>
        <td>{props.date}</td>
    </tr>);


}

export default TableRow;