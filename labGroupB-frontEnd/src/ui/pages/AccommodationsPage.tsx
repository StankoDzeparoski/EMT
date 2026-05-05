import { Box, Typography } from '@mui/material';
import useAccommodations from '../../hooks/useAccommodations';
import AccommodationsList from '../components/accommodation/AccommodationsList';

const AccommodationsPage = () => {
  const { accommodations, loading, error } = useAccommodations();

  return (
    <Box>
      <Typography variant="h4" sx={{ marginBottom: 4, fontWeight: 'bold' }}>
        Accommodations
      </Typography>
      <AccommodationsList accommodations={accommodations} loading={loading} error={error} />
    </Box>
  );
};

export default AccommodationsPage;
