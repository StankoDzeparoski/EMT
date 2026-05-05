import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Alert,
  Button,
  Chip,
  Rating,
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow
} from '@mui/material';
import { ArrowBack } from '@mui/icons-material';
import useAccommodation from '../../hooks/useAccommodation';

const AccommodationDetailPage = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { accommodation, loading, error } = useAccommodation(Number(id));

  if (loading) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  if (error || !accommodation) {
    return (
      <Container maxWidth="lg" sx={{ paddingY: 4 }}>
        <Alert severity="error">
          {error ? `Error loading accommodation: ${error.message}` : 'Accommodation not found'}
        </Alert>
        <Button onClick={() => navigate('/accommodations')} sx={{ marginTop: 2 }}>
          <ArrowBack sx={{ marginRight: 1 }} />
          Back to Accommodations
        </Button>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ paddingY: 4 }}>
      <Button onClick={() => navigate('/accommodations')} sx={{ marginBottom: 2 }}>
        <ArrowBack sx={{ marginRight: 1 }} />
        Back to Accommodations
      </Button>

      <Card sx={{ marginBottom: 3 }}>
        <CardContent>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'start', marginBottom: 2 }}>
            <Typography variant="h4" component="h1" sx={{ fontWeight: 'bold' }}>
              {accommodation.name}
            </Typography>
            <Chip
              label={accommodation.occupied ? 'Occupied' : 'Available'}
              color={accommodation.occupied ? 'error' : 'success'}
              variant="outlined"
            />
          </Box>

          <Box sx={{ marginBottom: 3, display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: 2 }}>
            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Category:</strong>
              </Typography>
              <Typography>{accommodation.category}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Condition:</strong>
              </Typography>
              <Typography>{accommodation.condition}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Number of Rooms:</strong>
              </Typography>
              <Typography>{accommodation.numRooms}</Typography>
            </Box>

            <Box>
              <Typography color="textSecondary" gutterBottom>
                <strong>Average Rating:</strong>
              </Typography>
              <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                <Rating value={accommodation.averageRating || 0} readOnly size="small" precision={0.5} />
                <Typography variant="body2">({accommodation.averageRating?.toFixed(1) || 'N/A'})</Typography>
              </Box>
            </Box>
          </Box>

          {accommodation.host && (
            <Box sx={{ marginTop: 3, padding: 2, backgroundColor: '#f5f5f5', borderRadius: 1 }}>
              <Typography variant="h6" sx={{ marginBottom: 1, fontWeight: 'bold' }}>
                Host Information
              </Typography>
              <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))', gap: 2 }}>
                <Box>
                  <Typography color="textSecondary">
                    <strong>Name:</strong>
                  </Typography>
                  <Typography>{accommodation.host.name}</Typography>
                </Box>
                <Box>
                  <Typography color="textSecondary">
                    <strong>Surname:</strong>
                  </Typography>
                  <Typography>{accommodation.host.surname}</Typography>
                </Box>
              </Box>
            </Box>
          )}
        </CardContent>
      </Card>

      {accommodation.reviews && accommodation.reviews.length > 0 && (
        <Card>
          <CardContent>
            <Typography variant="h6" sx={{ marginBottom: 2, fontWeight: 'bold' }}>
              Reviews ({accommodation.reviews.length})
            </Typography>

            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow sx={{ backgroundColor: '#f5f5f5' }}>
                    <TableCell>
                      <strong>Rating</strong>
                    </TableCell>
                    <TableCell>
                      <strong>Comment</strong>
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {accommodation.reviews.map((review, index) => (
                    <TableRow key={index}>
                      <TableCell>
                        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                          <Rating value={review.rating} readOnly size="small" />
                          <Typography variant="body2">({review.rating})</Typography>
                        </Box>
                      </TableCell>
                      <TableCell>{review.comment}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </CardContent>
        </Card>
      )}
    </Container>
  );
};

export default AccommodationDetailPage;
