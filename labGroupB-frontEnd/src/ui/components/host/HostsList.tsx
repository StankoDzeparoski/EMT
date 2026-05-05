import { Grid, CircularProgress, Box, Alert } from '@mui/material';
import HostCard from './HostCard';
import type { DisplayHostDto } from '../../../api/types/Host';

interface HostsListProps {
  hosts: DisplayHostDto[];
  loading: boolean;
  error: Error | null;
}

const HostsList = ({ hosts, loading, error }: HostsListProps) => {
  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', padding: 4 }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return <Alert severity="error">Error loading hosts: {error.message}</Alert>;
  }

  if (hosts.length === 0) {
    return <Alert severity="info">No hosts available</Alert>;
  }

  return (
    <Grid container spacing={3}>
      {hosts.map((host) => (
        <Grid item xs={12} sm={6} md={4} key={host.id}>
          <HostCard host={host} />
        </Grid>
      ))}
    </Grid>
  );
};

export default HostsList;
