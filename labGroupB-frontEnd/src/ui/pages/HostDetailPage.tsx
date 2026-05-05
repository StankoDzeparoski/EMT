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
import useHost from '../../hooks/useHost';

const HostDetailPage = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { host, loading, error } = useHost(Number(id));

  if (loading) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  if (error || !host) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Alert severity="error">
          {error ? `Error loading host: ${error.message}` : 'Host not found'}
        </Alert>
        <Button onClick={() => navigate('/hosts')} sx={{ marginTop: 2 }}>
          <ArrowBack sx={{ marginRight: 1 }} />
          Back to Hosts
        </Button>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ paddingY: 4 }}>
      <Button onClick={() => navigate('/hosts')} sx={{ marginBottom: 2 }}>
        <ArrowBack sx={{ marginRight: 1 }} />
        Back to Hosts
      </Button>

      <Card>
        <CardContent>
          <Typography variant="h4" component="h1" sx={{ marginBottom: 3, fontWeight: 'bold' }}>
            {host.name} {host.surname}
          </Typography>

          <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))', gap: 3 }}>
            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Host ID:</strong>
              </Typography>
              <Typography>{host.id}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Name:</strong>
              </Typography>
              <Typography>{host.name}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Surname:</strong>
              </Typography>
              <Typography>{host.surname}</Typography>
            </Box>
          </Box>

          {host.country && (
            <Box sx={{ marginTop: 4, padding: 2, backgroundColor: '#f5f5f5', borderRadius: 1 }}>
              <Typography variant="h6" sx={{ marginBottom: 2, fontWeight: 'bold' }}>
                Country Information
              </Typography>
              <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: 2 }}>
                <Box>
                  <Typography color="textSecondary">
                    <strong>Country ID:</strong>
                  </Typography>
                  <Typography>{host.country.id}</Typography>
                </Box>
                <Box>
                  <Typography color="textSecondary">
                    <strong>Country Name:</strong>
                  </Typography>
                  <Typography>{host.country.name}</Typography>
                </Box>
                <Box>
                  <Typography color="textSecondary">
                    <strong>Continent:</strong>
                  </Typography>
                  <Typography>{host.country.continent}</Typography>
                </Box>
              </Box>
            </Box>
          )}
        </CardContent>
      </Card>
    </Container>
  );
};

export default HostDetailPage;
