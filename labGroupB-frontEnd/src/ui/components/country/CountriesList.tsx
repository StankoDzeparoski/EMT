import { Grid, CircularProgress, Box, Alert } from '@mui/material';
import CountryCard from './CountryCard';
import type { DisplayCountryDto } from '../../../api/types/Country';

interface CountriesListProps {
  countries: DisplayCountryDto[];
  loading: boolean;
  error: Error | null;
}

const CountriesList = ({ countries, loading, error }: CountriesListProps) => {
  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return <Alert severity="error">Error loading countries: {error.message}</Alert>;
  }

  if (countries.length === 0) {
    return <Alert severity="info">No countries available</Alert>;
  }

  return (
    <Grid container spacing={3}>
      {countries.map((country) => (
        <Grid item xs={12} sm={6} md={4} key={country.id}>
          <CountryCard country={country} />
        </Grid>
      ))}
    </Grid>
  );
};

export default CountriesList;
