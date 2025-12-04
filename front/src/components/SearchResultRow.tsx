function SearchResultRow(props: any) {

    function handleClickDelete() {
        fetch("http://localhost:8080/admin/delete?id=" + props.id, {
            method: "DELETE",
            headers: {
                "Authorization": 'Bearer ' + sessionStorage.getItem("token")
            }
        })
            .then((res) => {
                if (!res.ok) { throw new Error(`Erreur HTTP : ${res.status}`) }
                else {
                    props.setResult(
                        props.result.filter((elem: any) => {
                            return elem.id !== props.id;
                        })
                    );
                }

            })
            .catch((e) => console.log(e.message))
    }

    return (<tr>

        <td>{props.id}</td>

        <td>{props.login}</td>
        <td>
            <button onClick={() => {
                handleClickDelete();
            }} >DELETE</button>
        </td>
    </tr>);


}

export default SearchResultRow;