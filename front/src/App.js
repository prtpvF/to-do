import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import AddUser from "./users/AddUser";
import Login from './pages/Login';
import AddTask from "./tasks/AddTask";
import ViewAllTasks from "./tasks/ViewAllTasks";
import Registration from "./pages/Registration";
import ViewTask from "./tasks/ViewTask";


function App() {
  return (

    <div className="App">
        <Router>
            <Navbar />

            <Routes>
                <Route exact path="/" element={<Home />} />
                <Route exact path="/adduser" element={<AddUser />} />
                <Route exact path="/login" element={<Login />} />
                <Route exact path="/add/task" element={<AddTask/>}/>
                <Route exact path="/view/all/tasks" element={<ViewAllTasks/>}/>
                <Route exact path="/registration" element={<Registration/>}/>
                <Route exact path="/view/task/:id" element={<ViewTask/>}/>
            </Routes>
        </Router>

    </div>
  );
}

export default App;
