import { Navigate, Outlet, useLocation } from 'react-router-dom';
import useAuth from '../hooks/useAuth';

const RequireAuth = () => {
  const { auth } = useAuth();
  const location = useLocation();

  return auth?.email ? <Outlet /> : <Navigate to="/login" state={{ from: location }} replace />;
};

// Role based auth
// const RequireAuth = ({ allowedRoles }) => {
//   const { auth } = useAuth();
//   const location = useLocation();

//   return auth?.roles?.find((role) => allowedRoles?.includes(role)) ? (
//     <Outlet />
//   ) : auth?.email ? (
//     <Navigate to="/unauthorized" state={{ from: location }} replace />
//   ) : (
//     <Navigate to="/login" state={{ from: location }} replace />
//   );
// };

export default RequireAuth;
