import { Box, Typography } from '@mui/material';
import useCountries from '../../hooks/useCountries';
import CountriesList from '../components/country/CountriesList';

const CountriesPage = () => {
  const { countries, loading, error } = useCountries();

  return (
    <Box>
      <Typography variant="h4" sx={{ marginBottom: 4, fontWeight: 'bold' }}>
        Countries
      </Typography>
      <CountriesList countries={countries} loading={loading} error={error} />
    </Box>
  );
};

export default CountriesPage;
