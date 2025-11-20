import { useEffect, useState } from "react";
import TableRow from "./TableRow";


interface ScoreDto {
    score: number;
    date: string;
    owner: string;
};


function LeaderBoard() {
    const [leaderGlobalData, setLeaderGlobalData] = useState([]);
    const [leaderUserData, setLeaderUserData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setIsLoading(true); //indique que la requête démarre
        setError(null); // efface les anciennes erreurs
        fetch("http://localhost:8080/leaderboard")
            .then((res) => {
                if (!res.ok) throw new Error(`Erreur HTTP : ${res.status}`); //vérifie la réponse
                return res.json(); // transforme le JSON en objet JavaScript si réponse ok
            })
            .then((leaderGlobalData) => setLeaderGlobalData(leaderGlobalData)) // on stocke les données dans le state.
            .catch((err) => setError(err.message)) // capture les erreurs.
            .finally(() => setIsLoading(false)); // se déclenche quoi qu’il arrive

        if (sessionStorage.getItem("id")) {
            fetch("http://localhost:8080/userscores?id=" + sessionStorage.getItem("id"))
                .then((res) => {
                    if (!res.ok) throw new Error(`Erreur HTTP : ${res.status}`); //vérifie la réponse
                    return res.json(); // transforme le JSON en objet JavaScript si réponse ok
                })
                .then((leaderUserData) => setLeaderUserData(leaderUserData)) // on stocke les données dans le state.
                .catch((err) => setError(err.message)) // capture les erreurs.
                .finally(() => setIsLoading(false)); // se déclenche quoi qu’il arrive
        }



    }, []);


    if (isLoading) return <p>Chargement...</p>; // Affichage lors du chargement
    if (error) return <p>Erreur : {error}</p>; // Affichage si erreur
    if (!leaderGlobalData) return <p>Aucune donnée trouvée.</p>; // Affichage si aucun donnée trouvée
    return (

        <>
            {
                sessionStorage.getItem("id") ?
                    <>
                        <h2>Personal Ranking: </h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Rank</th>
                                    <th>Score</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {leaderUserData!.map((elem: ScoreDto, index) =>
                                    <TableRow
                                        columns={3}
                                        key={index}
                                        index={index}
                                        score={elem.score}
                                        date={elem.date}
                                    />
                                )}
                            </tbody>
                        </table>
                    </>
                    : <> </>

            }

            <h2>Global Ranking: </h2>
            <table>
                <thead>
                    <tr>
                        <th>Rank</th>
                        <th>Login</th>
                        <th>Score</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    {leaderGlobalData!.map((elem: ScoreDto, index) =>
                        <TableRow
                            columns={4}
                            key={index}
                            index={index}
                            owner={elem.owner}
                            score={elem.score}
                            date={elem.date}
                        />
                    )}
                </tbody>
            </table>



        </>
    );



}


export default LeaderBoard;