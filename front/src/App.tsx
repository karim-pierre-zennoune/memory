import "./App.css";
import NavBar from "./components/NavBar";
import Menu from "./components/Menu";
import { Route, Routes } from "react-router-dom";
import GamePage from "./pages/GamePage";
import NotFoundPage from "./pages/NotFoundPage";
import HomePage from "./pages/HomePage";
import LeaderboardsPage from "./pages/LeaderboardsPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import AdminPage from "./pages/AdminPage";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <>
      <NavBar />

      <Routes>

        <Route path="/" element={< HomePage />} />
        <Route path="/game" element={<GamePage />} />


        {/* <Route path="/admin" element={<AdminPage />} /> */}


        <Route
          path="admin"
          element={
            <ProtectedRoute
              redirectPath="/"
              isAllowed={!!sessionStorage.getItem("id") && (
                sessionStorage.getItem("role") === "SUPER_ADMIN" ||
                sessionStorage.getItem("role") === "ADMIN")}
            >
              <AdminPage />
            </ProtectedRoute>
          }
        />



        <Route path="/leaderboards" element={< LeaderboardsPage />} />
        <Route path="/login" element={< LoginPage />} />
        <Route path="/register" element={< RegisterPage />} />
        {/* <Route path="/logout" element={< />} /> */}


        <Route path="*" element={<NotFoundPage />} />
      </Routes>


    </>
  );
}

export default App;



