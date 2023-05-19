import { Route, Routes } from 'react-router-dom';
import Admin from './components/Admin.js';
import Friends from './components/Friends.js';
import Groups from './components/Groups.js';
import Home from './components/Home';
import Layout from './components/Layout';
import LinkPage from './components/LinkPage.js';
import Login from './components/Login';
import Messages from './components/Messages.js';
import Missing from './components/Missing';
import PersistLogin from './components/PersistLogin.js';
import Profile from './components/Profile.js';
import Register from './components/Register';
import RequireAuth from './components/RequireAuth';
import Settings from './components/Settings.js';
import Unauthorized from './components/Unauthorized';

// const ROLES = {
//   User: [0, null],
//   Admin: 1,
// };

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* public routes */}
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="linkpage" element={<LinkPage />} />
        <Route path="unauthorized" element={<Unauthorized />} />

        {/* protected routes */}
        <Route element={<PersistLogin />}>

          <Route element={<RequireAuth />}>

            <Route path="/" element={<Home />} />
            <Route path="admin" element={<Admin />} />
            <Route path="profile" element={<Profile />} />
            <Route path="settings" element={<Settings />} />
            <Route path="friends" element={<Friends />} />
            <Route path="groups" element={<Groups />} />
            <Route path="messages" element={<Messages />} />

          </Route>

        </Route>

        {/* catch all */}
        <Route path="*" element={<Missing />} />
      </Route>
    </Routes >
  );
}

export default App;
