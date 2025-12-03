import { useEffect, useState } from "react";
import { UserSessionDto } from "./AdminComponent"
import RootResultRow from "./RootResultRow";

function RootComponent() {
    const [result, setResult] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [message, setMessage] = useState("Chargement en cour");


    useEffect(() => {

        fetch("http://localhost:8080/admin/search?param=" + searchTerm, {
            method: "GET",
            headers: {
                "Authorization": 'Bearer ' + sessionStorage.getItem("token")
            }

        })
            .then((response) => response.json())
            .then((json) => setResult(json))
            .catch(() => setMessage("ERROR"));

    }, [searchTerm]);

    function handleChange(event: any) {
        setSearchTerm(event?.target.value);
    }


    return (<>
        <input type="text" id="admin-search-bar" onChange={handleChange} />
        <section className="user-list">

            {
                result.length == 0 ? message :
                    <>
                        <table>
                            <tbody>
                                {
                                    result!.map((elem: UserSessionDto, index) =>
                                        < RootResultRow
                                            key={index}
                                            id={elem.id}
                                            login={elem.login}
                                            role={elem.role}
                                            result={result}
                                            setResult={setResult}
                                        />

                                    )
                                }
                            </tbody>
                        </table>
                    </>
            }
        </section>
    </>
    );
}

export default RootComponent;