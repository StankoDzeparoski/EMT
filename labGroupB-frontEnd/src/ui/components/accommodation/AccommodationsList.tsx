import { Grid, CircularProgress, Box, Alert } from '@mui/material';
import AccommodationCard from './AccommodationCard';
import type { DisplayAccommodationDto } from '../../../api/types/Accommodation';

interface AccommodationsListProps {
  accommodations: DisplayAccommodationDto[];
  loading: boolean;
  error: Error | null;
}

const AccommodationsList = ({
  accommodations,
  loading,
  error
}: AccommodationsListProps) => {
  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return <Alert severity="error">Error loading accommodations: {error.message}</Alert>;
  }

  if (accommodations.length === 0) {
    return <Alert severity="info">No accommodations available</Alert>;
  }

  return (
    <Grid container spacing={3}>
      {accommodations.map((accommodation) => (
        <Grid item xs={12} sm={6} md={4} key={accommodation.id}>
          <AccommodationCard accommodation={accommodation} />
        </Grid>
      ))}
    </Grid>
  );
};

export default AccommodationsList;
