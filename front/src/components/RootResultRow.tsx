function RootResultRow(props: any) {

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
                    console.log("delete OK");
                    props.setResult(
                        props.result.filter((elem: any) => {
                            return elem.id !== props.id;
                        })
                    );
                }

            })
            .catch((e) => console.log(e.message))
    }


    function handleClickPromote() {
        let action = props.role === 'ADMIN' ? "promote-admin" : "promote-user";

        console.log(action);
        console.log(props.id);
        fetch("http://localhost:8080/root/" + action, {
            method: "POST",
            body: JSON.stringify({
                id: props.id,
            }),
            headers: {
                "Content-Type": "application/json",
                "Authorization": 'Bearer ' + sessionStorage.getItem("token")
            }
        })
            .then((res) => {
                if (!res.ok) { throw new Error(`Erreur HTTP : ${res.status}`) }
                else {
                    // props.setResult(
                    //     props.result.filter((elem: any) => {
                    //         return elem.id !== props.id;
                    //     })
                    // );
                }

            })
            .catch((e) => console.log(e.message))
    }

    function handleClickDemote() {
        let action = props.role === 'ADMIN' ? "demote-admin" : "demote-root";
        fetch("http://localhost:8080/root/" + action, {
            method: "POST",
            body: JSON.stringify({
                id: props.id
            }),
            headers: {
                "Content-Type": "application/json",
                "Authorization": 'Bearer ' + sessionStorage.getItem("token")
            }
        })
            .then((res) => {
                if (!res.ok) { throw new Error(`Erreur HTTP : ${res.status}`) }
                else {
                    // props.setResult(
                    //     props.result.filter((elem: any) => {
                    //         return elem.id !== props.id;
                    //     })
                    // );
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
            }} >DEL</button>
        </td>
        <td>{props.role}</td>
        <td><button onClick={() => {
            handleClickPromote();
        }}>++</button></td>
        <td><button onClick={() => {
            handleClickDemote();
        }}>--</button></td>
    </tr>);


}

export default RootResultRow;