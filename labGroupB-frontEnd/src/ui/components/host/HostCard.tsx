import {
  Card,
  CardContent,
  CardHeader,
  Typography,
  Box
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import type { DisplayHostDto } from '../../../api/types/Host';

interface HostCardProps {
  host: DisplayHostDto;
}

const HostCard = ({ host }: HostCardProps) => {
  return (
    <RouterLink to={`/hosts/${host.id}`} style={{ textDecoration: 'none' }}>
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
          title={`${host.name} ${host.surname}`}
          titleTypographyProps={{ variant: 'h6' }}
        />
        <CardContent sx={{ flexGrow: 1 }}>
          <Box sx={{ marginBottom: 2 }}>
            <Typography color="textSecondary" gutterBottom>
              <strong>ID:</strong> {host.id}
            </Typography>

            <Typography color="textSecondary" gutterBottom>
              <strong>Name:</strong> {host.name}
            </Typography>

            <Typography color="textSecondary" gutterBottom>
              <strong>Surname:</strong> {host.surname}
            </Typography>

            {host.country && (
              <>
                <Typography color="textSecondary" gutterBottom>
                  <strong>Country:</strong> {host.country.name}
                </Typography>

                <Typography color="textSecondary" gutterBottom>
                  <strong>Continent:</strong> {host.country.continent}
                </Typography>
              </>
            )}
          </Box>
        </CardContent>
      </Card>
    </RouterLink>
  );
};

export default HostCard;
