import { useEffect, useState } from "react";
import SearchResultRow from "./SearchResultRow";

interface UserSessionDto {
    id: number;
    login: string;
}

function AdminComponent() {
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
        <input type="text" className="search-bar" onChange={handleChange} />
        <section className="user-list">


            {
                result.length == 0 ? message :

                    <>
                        <table>
                            <tbody>
                                {
                                    result!.map((elem: UserSessionDto, index) =>

                                        <SearchResultRow
                                            key={index}
                                            id={elem.id}
                                            login={elem.login}
                                        />
                                    )
                                }
                            </tbody>
                        </table>
                    </>




            }


        </section>
    </>


        // <section className="article-list">
        //     {articles.map((elem) => (
        //         <ArticleThumbnail  key={elem.id} image={elem.image} title={elem.title} content={elem.content} createdAt={elem.createdAt } />
        //     ))}
        // </section>
    );
}


export default AdminComponent;