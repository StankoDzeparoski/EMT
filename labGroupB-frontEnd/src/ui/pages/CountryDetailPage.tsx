import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Alert,
  Button,
  Container
} from '@mui/material';
import { ArrowBack } from '@mui/icons-material';
import useCountry from '../../hooks/useCountry';

const CountryDetailPage = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { country, loading, error } = useCountry(Number(id));

  if (loading) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  if (error || !country) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Alert severity="error">
          {error ? `Error loading country: ${error.message}` : 'Country not found'}
        </Alert>
        <Button onClick={() => navigate('/countries')} sx={{ marginTop: 2 }}>
          <ArrowBack sx={{ marginRight: 1 }} />
          Back to Countries
        </Button>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ paddingY: 4 }}>
      <Button onClick={() => navigate('/countries')} sx={{ marginBottom: 2 }}>
        <ArrowBack sx={{ marginRight: 1 }} />
        Back to Countries
      </Button>

      <Card>
        <CardContent>
          <Typography variant="h4" component="h1" sx={{ marginBottom: 3, fontWeight: 'bold' }}>
            {country.name}
          </Typography>

          <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))', gap: 3 }}>
            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Country ID:</strong>
              </Typography>
              <Typography>{country.id}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Name:</strong>
              </Typography>
              <Typography>{country.name}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Continent:</strong>
              </Typography>
              <Typography>{country.continent}</Typography>
            </Box>
          </Box>
        </CardContent>
      </Card>
    </Container>
  );
};

export default CountryDetailPage;
