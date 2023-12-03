import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import ErrorPage from "../pages/common/error/ErrorPage";
import Signin from "../pages/wishers/signin/Signin";

const ProtectedRoute = ({ isAuthenticated }) => {
    return isAuthenticated ? <Outlet /> : <Signin></Signin>;
};

export default ProtectedRoute;
