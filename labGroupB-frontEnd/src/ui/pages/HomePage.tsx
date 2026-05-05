import { Box, Container, Typography, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import { ArrowForward } from '@mui/icons-material';

const HomePage = () => {
  return (
    <Container maxWidth="md">
      <Box
        sx={{
          minHeight: 'calc(100vh - 300px)',
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          textAlign: 'center',
          gap: 4
        }}
      >
        <Typography variant="h3" component="h1" sx={{ fontWeight: 'bold', marginBottom: 2 }}>
          Welcome to Travel Accommodations
        </Typography>

        <Typography variant="h6" color="textSecondary" sx={{ marginBottom: 4, lineHeight: 1.6 }}>
          Discover amazing places to stay around the world. Browse accommodations, meet our
          hosts, and explore countries. Start your next adventure today!
        </Typography>

        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', justifyContent: 'center' }}>
          <Button
            variant="contained"
            size="large"
            component={RouterLink}
            to="/accommodations"
            endIcon={<ArrowForward />}
          >
            Explore Accommodations
          </Button>
          <Button
            variant="outlined"
            size="large"
            component={RouterLink}
            to="/hosts"
            endIcon={<ArrowForward />}
          >
            Meet Our Hosts
          </Button>
          <Button
            variant="outlined"
            size="large"
            component={RouterLink}
            to="/countries"
            endIcon={<ArrowForward />}
          >
            Browse Countries
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default HomePage;
