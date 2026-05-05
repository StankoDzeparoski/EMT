import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import Layout from './ui/components/layout/Layout';
import HomePage from './ui/pages/HomePage';
import AccommodationsPage from './ui/pages/AccommodationsPage';
import AccommodationDetailPage from './ui/pages/AccommodationDetailPage';
import HostsPage from './ui/pages/HostsPage';
import HostDetailPage from './ui/pages/HostDetailPage';
import CountriesPage from './ui/pages/CountriesPage';
import CountryDetailPage from './ui/pages/CountryDetailPage';
import LogsPage from './ui/pages/LogsPage';
import RegisterPage from './ui/pages/RegisterPage';
import LoginPage from './ui/pages/LoginPage';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2'
    },
    secondary: {
      main: '#dc004e'
    }
  },
  typography: {
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif'
  }
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter>
        <Routes>
          <Route element={<Layout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/accommodations" element={<AccommodationsPage />} />
            <Route path="/accommodations/:id" element={<AccommodationDetailPage />} />
            <Route path="/hosts" element={<HostsPage />} />
            <Route path="/hosts/:id" element={<HostDetailPage />} />
            <Route path="/countries" element={<CountriesPage />} />
            <Route path="/countries/:id" element={<CountryDetailPage />} />
            <Route path="/logs" element={<LogsPage />} />
          </Route>
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
