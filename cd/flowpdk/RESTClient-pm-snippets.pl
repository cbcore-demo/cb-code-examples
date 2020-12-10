## Default encodePayload looks like this:

sub encodePayload {
    my ($self, $payload) = @_;

    return encode_json($payload);
}

## If you have raw JSON to send, then need to override, e.g:
## This example shows overriding only for specific methods

sub encodePayload {
    my ($self, $payload) = @_;

    if ($self->method eq 'patchRoute' or 'patchDeployment' or 'patchDeploymentConfig') {
        # JSON
        my $body = $payload->{body};
        return $body;
    }

    return encode_json($payload);
}