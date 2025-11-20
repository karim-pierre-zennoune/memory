import { useState } from "react";
import { useNavigate } from "react-router-dom";

function RegisterForm() {
    const navigate = useNavigate();
    const [error, setError] = useState(null);
    // const [isLoading, setIsLoading] = useState(false);

    function handleSubmit(event: any) {
        event.preventDefault();
        // setIsLoading(true);
        setError(null);
        fetch("http://localhost:8080/register", {
            method: "POST",
            body: JSON.stringify({
                login: event.target[1].value,
                password: event.target[2].value
            }),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((res) => {
                // if (!res.ok) throw new Error("Erreur serveur");
                return res.json();
            })
            .then((data) => {
                console.log("register OK");
                // reset ou redirection
                navigate("/login");
            })
            .catch((err) => setError(err.message))
        // .finally(() => setIsLoading(false));
    }





    return (
        <div className="responsive-form" onSubmit={handleSubmit}>
            <form action="">

                <input type="hidden" />

                <label htmlFor="form-login" hidden></label>
                <input id="form-login" type="text" name="login" placeholder="Login" title="Login" autoComplete="username"
                    autoFocus required></input>


                <label htmlFor="form-password" hidden></label>
                <input id="form-password" type="password" name="password" placeholder="Password" title="Password"
                    autoComplete="new-password" required></input>

                <label htmlFor="form-password-confirm"></label>
                <input id="form-password-confirm" type="password" name="password-confirm" placeholder="Confirm Password"
                    autoComplete="new-password" title="New Password" required></input>

                <button type="submit" name="submit" value="Envoyer">Submit</button>
            </form>

        </div>
    );
}

export default RegisterForm;