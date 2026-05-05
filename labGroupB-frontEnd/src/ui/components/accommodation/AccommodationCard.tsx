import {
  Card,
  CardContent,
  CardHeader,
  Typography,
  Box,
  Chip,
  Rating
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import type { DisplayAccommodationDto } from '../../../api/types/Accommodation';

interface AccommodationCardProps {
  accommodation: DisplayAccommodationDto;
}

const AccommodationCard = ({ accommodation }: AccommodationCardProps) => {
  return (
    <RouterLink to={`/accommodations/${accommodation.id}`} style={{ textDecoration: 'none' }}>
      <Card
        sx={{
          height: '100%',
          display: 'flex',
          flexDirection: 'column',
          cursor: 'pointer',
          transition: 'all 0.3s ease',
          '&:hover': {
            boxShadow: '0 8px 24px rgba(0, 0, 0, 0.15)',
            transform: 'translateY(-4px)'
          }
        }}
      >
        <CardHeader
          title={accommodation.name}
          subheader={accommodation.category}
          titleTypographyProps={{ variant: 'h6' }}
        />
        <CardContent sx={{ flexGrow: 1 }}>
          <Box sx={{ marginBottom: 2 }}>
            <Chip
              label={accommodation.occupied ? 'Occupied' : 'Available'}
              color={accommodation.occupied ? 'error' : 'success'}
              variant="outlined"
              size="small"
            />
          </Box>

          <Typography color="textSecondary" gutterBottom>
            <strong>Condition:</strong> {accommodation.condition}
          </Typography>

          <Typography color="textSecondary" gutterBottom>
            <strong>Rooms:</strong> {accommodation.numRooms}
          </Typography>

          {accommodation.host && (
            <Typography color="textSecondary" gutterBottom>
              <strong>Host:</strong> {accommodation.host.name} {accommodation.host.surname}
            </Typography>
          )}

          <Box sx={{ marginTop: 2, display: 'flex', alignItems: 'center', gap: 1 }}>
            <Typography variant="body2" sx={{ fontWeight: 'bold' }}>
              Rating:
            </Typography>
            <Rating
              value={accommodation.averageRating || 0}
              readOnly
              size="small"
              precision={0.5}
            />
            <Typography variant="body2" color="textSecondary">
              ({accommodation.averageRating?.toFixed(1) || 'N/A'})
            </Typography>
          </Box>
        </CardContent>
      </Card>
    </RouterLink>
  );
};

export default AccommodationCard;
